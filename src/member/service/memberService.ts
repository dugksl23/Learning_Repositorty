import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import MemberEntity from '../entities/memberEntity';
import { MemberRepository } from '../repository/memberRepository';
import * as bcrypt from 'bcrypt';
import { Connection } from 'typeorm';
import { Builder } from 'builder-pattern';
import { promises } from 'fs';

@Injectable()
export class MemberService {
  private repository: MemberRepository;
  constructor(private readonly connection: Connection) {
    this.repository = this.connection.getCustomRepository(MemberRepository);
  }

  async signIn(memberDto: MemberDto) {
    const existMember: Promise<MemberEntity> = this.repository.findOne(
      memberDto.memberName,
    );
    if (
      !existMember ||
      !bcrypt.compare(memberDto.password, (await existMember).password)
    ) {
      throw new HttpException(
        'Invalid memberName/password',
        HttpStatus.BAD_REQUEST,
      );
    }

    return existMember;
  }

  async validateMember(memberDto: MemberDto) {
    const memberName = await this.repository.findByMemberName(memberDto);

    if (memberName === null) {
      throw new HttpException('invalid MemberName', HttpStatus.NOT_FOUND);
    }

    return true;
  }

  async createMember(memberDto: MemberDto) {
    memberDto.memberName = 'root';
    memberDto.password = 'root';
    const member = Builder<MemberEntity>()
      .memberName(memberDto.memberName)
      .password(memberDto.password)
      .roles(memberDto.roles)
      .build();

    return await this.repository.createMember(member);
  }

  findAll() {
    const members = this.repository.findAll();
    return members;
  }
}

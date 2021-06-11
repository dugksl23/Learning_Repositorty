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
    const existMember = await this.repository.findByMemberName(memberDto);
    if (
      !existMember ||
      !bcrypt.compare(memberDto.password, existMember.password)
    ) {
      throw new HttpException(
        'Invalid memberName/password',
        HttpStatus.BAD_REQUEST,
      );
    }

    return existMember;
  }

  async validateMember(memberDto: MemberDto) {
    const member = await this.repository.findByMemberName(memberDto);
    if (member === null) {
      throw new HttpException('invalid MemberName', HttpStatus.NOT_FOUND);
    }
    return member;
  }

  async createManager(memberDto: MemberDto) {
    memberDto.memberName = 'root';
    memberDto.password = 'root';
    const existOrNot = await this.existManager(memberDto.memberName);
    console.log(existOrNot);
    if (existOrNot) {
      return false;
    }

    const member = Builder<MemberEntity>()
      .memberName(memberDto.memberName)
      .password(memberDto.password)
      .roles(memberDto.roles)
      .build();
    const result = await this.repository.createMember(member);
    return true;
  }

  async existManager(name: string) {
    const result = await this.repository.existManager(name);
    console.log(JSON.stringify(result));
    if (typeof result !== 'undefined' || JSON.stringify(result) === null) {
      return true;
    }
    return false;
  }

  async findAll() {
    const members = await this.repository.findAll();
    return members;
  }
}

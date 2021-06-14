import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import MemberEntity from '../entities/memberEntity';
import { MemberRepository } from '../repository/memberRepository';
import * as bcrypt from 'bcrypt';
import { Connection } from 'typeorm';
import { Builder } from 'builder-pattern';
import { RoleRepository } from '../repository/roleRepository';

@Injectable()
export class MemberService {
  private memberRepository: MemberRepository;
  private roleRepository: RoleRepository;

  constructor(private readonly connection: Connection) {
    this.memberRepository =
      this.connection.getCustomRepository(MemberRepository);
    this.roleRepository = this.connection.getCustomRepository(RoleRepository);
  }

  async signIn(memberDto: MemberDto) {
    const existMember: MemberEntity =
      await this.memberRepository.findByMemberName(memberDto);

    if (
      typeof existMember === 'undefined' ||
      !(await bcrypt.compare(memberDto.password, existMember.password))
    ) {
      throw new HttpException(
        'Invalid memberName/password',
        HttpStatus.BAD_REQUEST,
      );
    }

    return existMember;
  }

  async validateMember(memberDto: MemberDto) {
    const member = await this.memberRepository.findByMemberName(memberDto);
    console.log(member);
    if (typeof member !== 'undefined') {
      throw new HttpException('invalid Member', HttpStatus.NOT_FOUND);
    }
    return member;
  }

  async createManager(managerName: string) {
    const existOrNot = await this.existManager(managerName);

    if (existOrNot) {
      return false;
    }

    await this.roleRepository.createRole();
    const role = await this.roleRepository.findRole(process.env.managerRoleNo);

    const member = Builder<MemberEntity>()
      .memberName(process.env.managerName)
      .password(process.env.managerPassword)
      .roles(role)
      .build();
    return await this.memberRepository.createMember(member);
  }

  async existManager(managerName: string) {
    const existOrNot = await this.memberRepository.existManager(managerName);
    if (typeof existOrNot !== 'undefined') {
      return true;
    }
    return false;
  }

  async findAll() {
    const members: MemberEntity[] = await this.memberRepository.findAll();
    return members;
  }

  async validateManagerRole(memberName: string) {
    return await this.memberRepository.validateManagerRole(memberName);
  }
}

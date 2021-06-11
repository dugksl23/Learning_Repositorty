import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import MemberEntity from '../entities/memberEntity';
import { MemberRepository } from '../repository/memberRepository';
import * as bcrypt from 'bcrypt';
import { Repository } from 'typeorm/repository/Repository';
import { InjectRepository } from '@nestjs/typeorm';

const dummyMember = new MemberEntity('root', 'root');

@Injectable()
export class MemberService {
  constructor(
    //private readonly memberRepository1: Repository<MemberRepository>,
    @InjectRepository(MemberRepository)
    private readonly memberRepository: MemberRepository,
  ) {}

  async signIn(memberDto: MemberDto) {
    //const member = this.memberRepository.findOne(memberDto.memberName);
    const existMember: MemberEntity = dummyMember;
    existMember.password = await bcrypt.hash(existMember.password, 10);
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
    const memberName = await this.memberRepository.findByMemberName(memberDto);

    if (memberName === null) {
      throw new HttpException('invalid MemberName', HttpStatus.NOT_FOUND);
    }

    return true;
  }

  async createMember(memberDto: MemberDto) {
    //console.log(this.memberRepository);
    const member = await this.memberRepository.createMember(memberDto);
  }
}

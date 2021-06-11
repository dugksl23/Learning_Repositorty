import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import MemberEntity from '../entities/memberEntity';
import { MemberRepository } from '../repository/memberRepository';
import * as bcrypt from 'bcrypt';
import { Connection } from 'typeorm';
const dummyMember = new MemberEntity('root', 'root');

@Injectable()
export class MemberService {
  private repository: MemberRepository;
  constructor(private readonly connection: Connection) {
    this.repository = this.connection.getCustomRepository(MemberRepository);
  }

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
    const memberName = await this.repository.findByMemberName(memberDto);

    if (memberName === null) {
      throw new HttpException('invalid MemberName', HttpStatus.NOT_FOUND);
    }

    return true;
  }

  async createMember(memberDto: MemberDto) {
    const member = await this.repository.createMember(memberDto);
  }

  findAll() {
    const members = this.repository.findAll();
    return members;
  }
}

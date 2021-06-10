import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import MemberEntity from '../entities/memberEntity';
import { MemberRepository } from '../repository/memberRepository';
import * as bcrypt from 'bcrypt';
import { Token } from '../decorator/member.decorator';

// var dummyMember = {
//   id: '001',
//   memberName: 'root',
//   password: 'root',
//   roles: 1,
//   createdDate: new Date(),
//   updatedDate: new Date(),
//   lastLoginDate: new Date(),
// };

const dummyMember = new MemberEntity('root', 'root', 1);

@Injectable()
export class MemberService {
  constructor(private readonly memberRepository: MemberRepository) {}

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
}

import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import MemberEntity from '../entities/memberEntity';
import { MemberRepository } from '../repository/memberRepository';

@Injectable()
export class MemberService {
  constructor(private readonly memberRepository: MemberRepository) {}

  async signIn(memberDto: MemberDto) {
    const member = this.memberRepository.findOne(memberDto);
    // if (!member || member.comparePassword(memberDto.password)) {
    //   throw new HttpException(
    //     'Invalid memberName/password',
    //     HttpStatus.BAD_REQUEST,
    //   );
    // }

    return member;
  }
}

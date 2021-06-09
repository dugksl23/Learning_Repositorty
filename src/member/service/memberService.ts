import { Injectable } from '@nestjs/common';
import MemberDto from '../dto/memberDto';
import { MemberRepository } from '../repository/memberRepository';

@Injectable()
export class MemberService {
  constructor(private readonly memberRepository: MemberRepository) {}

  signIn(dd: string): String {
    console.log('옵니다.');

    return '로그인 됨';
  }

  findMember(memberDto: MemberDto) {
    const member = memberDto;
    return this.memberRepository.findMember(member);
  }
}

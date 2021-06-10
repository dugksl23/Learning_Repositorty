import { Module } from '@nestjs/common';
import { MemberService } from '../member/service/memberService';
import { MemberController } from '../member/controller/memberController';
import { TypeOrmModule } from '@nestjs/typeorm';
import { MemberRepository } from './repository/memberRepository';

@Module({
  imports: [TypeOrmModule.forFeature([MemberRepository])],
  controllers: [MemberController],
  providers: [MemberService],
})
export class MemberModule {}

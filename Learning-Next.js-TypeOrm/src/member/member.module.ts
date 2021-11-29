import { Module } from '@nestjs/common';
import { MemberService } from '../member/service/memberService';
import { MemberController } from '../member/controller/memberController';
import { TypeOrmModule } from '@nestjs/typeorm';
import { MemberRepository } from './repository/memberRepository';
import MemberEntity from './entities/memberEntity';

@Module({
  imports: [TypeOrmModule.forFeature([MemberEntity])],
  controllers: [MemberController],
  providers: [MemberService, MemberRepository],
  exports: [MemberRepository],
})
export class MemberModule {}

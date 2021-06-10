import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { WinstonModule } from 'nest-winston';
import { MemberController } from './member/controller/memberController';
import { MemberService } from './member/service/memberService';
import { StakingsModule } from './stakings/stakings.module';
import { MiningsModule } from './minings/minings.module';
import { AddressesModule } from './addresses/addresses.module';
import { BridgingsModule } from './bridgings/bridgings.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { MemberRepository } from './member/repository/memberRepository';

@Module({
  imports: [
    WinstonModule.forRoot({
      // options
    }),
    StakingsModule,
    MiningsModule,
    AddressesModule,
    BridgingsModule,
    TypeOrmModule.forRoot(),
  ],

  controllers: [AppController, MemberController],
  providers: [AppService, MemberService, MemberRepository],
})
export class AppModule {}

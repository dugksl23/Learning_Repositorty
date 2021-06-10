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
import { Connection, getConnectionOptions } from 'typeorm';
import { MemberModule } from './member/member.module';
@Module({
  imports: [
    WinstonModule.forRoot({
      // options
    }),
    StakingsModule,
    MiningsModule,
    AddressesModule,
    //MemberModule,
    BridgingsModule,
    TypeOrmModule.forRoot({
      synchronize: true,
      autoLoadEntities: true,
    }),
    TypeOrmModule.forRootAsync({
      useFactory: async () =>
        Object.assign(await getConnectionOptions(), {
          autoLoadEntities: true,
        }),
    }),
  ],
  controllers: [AppController, MemberController],
  providers: [AppService, MemberRepository, MemberService],
})
export class AppModule {
  constructor(private connection: Connection) {}
}

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
import MemberEntity from './member/entities/memberEntity';
@Module({
  imports: [
    WinstonModule.forRoot({
      // options
    }),
    StakingsModule,
    MiningsModule,
    AddressesModule,
    MemberModule,
    BridgingsModule,
    TypeOrmModule.forRoot({
      synchronize: true,
      autoLoadEntities: true,
      type: 'mariadb',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: 'root',
      database: 'testDB',
      entities: [],
    }),
    TypeOrmModule.forRootAsync({
      useFactory: async () =>
        Object.assign(await getConnectionOptions(), {
          autoLoadEntities: true,
        }),
    }),
  ],
  controllers: [AppController],
  providers: [AppService, MemberRepository],
})
export class AppModule {
  constructor(private connection: Connection) {}
}

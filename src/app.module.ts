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
import { Connection, getConnectionOptions } from 'typeorm';
import { MemberModule } from './member/member.module';
import MemberEntity from './member/entities/memberEntity';

import { RoleModule } from './member/role.module';
@Module({
  imports: [
    WinstonModule.forRoot({
      // options
    }),
    StakingsModule,
    MiningsModule,
    AddressesModule,
    MemberModule,
    //RoleModule,
    BridgingsModule,
    TypeOrmModule.forRoot({
      synchronize: false,
      autoLoadEntities: true,
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: 'root',
      database: 'testDB',
      entities: ['${rootDir}/entities/**/*.ts'],
      //'${rootDir}/entities/**/*.ts'
    }),
    TypeOrmModule.forRootAsync({
      useFactory: async () =>
        Object.assign(await getConnectionOptions(), {
          autoLoadEntities: true,
        }),
    }),
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {
  constructor(private connection: Connection) {}
}

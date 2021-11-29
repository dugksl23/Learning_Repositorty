import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import MemberDto from './member/dto/memberDto';
import RoleEntity from './member/entities/roleEntity';
import { getManager, getMongoManager } from 'typeorm';
import { MemberService } from '../src/member/service/memberService';

async function bootstrap() {
  const fs = require('fs');

  // const keyFile  = fs.readFileSync( '/etc/nginx/ssl/lucasbtc.net/nopass_lucasbtc.net.key');
  // const certFile = fs.readFileSync( '/etc/nginx/ssl/lucasbtc.net/ca_bundle.crt');

  const app = await NestFactory.create(AppModule, {
    // httpsOptions: { key: keyFile, cert: certFile },
  });

  const config = new DocumentBuilder()
    .setTitle('Cats example')
    .setDescription('The cats API description')
    .setVersion('1.0')
    .addTag('cats')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, document);
  await app.listen(80);
}
bootstrap();

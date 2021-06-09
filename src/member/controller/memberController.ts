import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  Req,
  Query,
  Res,
  HttpStatus,
  Logger,
} from '@nestjs/common';
import { Request, response, Response } from 'express';

import { ApiNoContentResponse, ApiTags } from '@nestjs/swagger';
import { MemberService } from '../service/memberService';
import MemberDto from '../dto/memberDto';

@Controller('/api/member')
export class MemberController {
  private readonly logger = new Logger();
  constructor(private readonly memberService: MemberService) {}

  @Get('/signIn')
  @ApiTags('멤버 로그인')
  @ApiNoContentResponse({
    description:
      '관리자 로그인 시도 및 관리자의 role과 permission을 가진 member 객체를 return 한다.',
  })
  async signIn(@Req() memberDto: MemberDto, @Res() response: Response) {
    const member: String = this.memberService.signIn('dd');
    return response.status(HttpStatus.OK).json(member);
  }

  @Post('/findMember')
  findMember(@Body() memberDto: MemberDto, @Res() response: Response) {
    Logger.log(memberDto.id);
    const member = this.memberService.findMember(memberDto);
    return response.status(HttpStatus.OK).json(member);
  }
}

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
  UseGuards,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { Request, response, Response } from 'express';
import { ApiNoContentResponse, ApiTags } from '@nestjs/swagger';
import { MemberService } from '../service/memberService';
import MemberDto from '../dto/memberDto';
import { AuthGuard } from 'src/auth/auth.gaurd';
import { Token } from '../decorator/member.decorator';

@Controller('/api/member')
export class MemberController {
  private readonly logger = new Logger();
  constructor(private readonly memberService: MemberService) {}

  @Post('/signIn')
  @ApiTags('멤버 로그인')
  @ApiNoContentResponse({
    description:
      '관리자 로그인 시도 및 관리자의 role과 permission을 가진 member 객체를 return 한다.',
  })
  signIn(
    @Body() memberDto: MemberDto,
    @Res() response: Response,
    @Token(new ValidationPipe({ validateCustomDecorators: true })) token,
  ) {
    const member = this.memberService.signIn(memberDto);
    console.log(token);
    return response.status(HttpStatus.OK).json(token);
  }

  // @Post('/findMember')
  // findOne(@Body() memberDto: MemberDto, @Res() response: Response) {
  //   const member = this.memberService.findMember(memberDto);
  //   return response.status(HttpStatus.OK).json(member);
  // }

  @Get('/findAll')
  @UseGuards(new AuthGuard())
  // useGuards 데코레이터를 이용하여 토큰 요청이 필요한 API임을 알립니다.
  // UseGuards 안에는 2번 제목에서 선언했던 AuthGuard 클래스를 넣어줍니다.
  findAll(
    @Token(new ValidationPipe({ validateCustomDecorators: true })) token,
  ) {
    return response.status(HttpStatus.OK).json(token);
  }
}

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
  @ApiTags('관리자 로그인')
  async signIn(
    @Body() memberDto: MemberDto,
    @Res() response: Response,
    // @Token(new ValidationPipe({ validateCustomDecorators: true })) token, //new ValidationPipe({ validateCustomDecorators: true })
  ) {
    const existMember = this.memberService.signIn(memberDto);
    return response
      .status(HttpStatus.OK)
      .json((await existMember).toResponseObject(true));
  }

  @Get('/validateMember')
  @ApiTags('관리자 아이디 확인')
  validateMember(@Req() memberDto: MemberDto, @Res() response: Response) {
    const memberStatus = this.memberService.validateMember(memberDto);
    return response.status(HttpStatus.OK).json(memberStatus);
  }

  @Get('/findAll')
  @ApiTags('모든 유저의 정보 조회')
  @UseGuards(new AuthGuard())
  // useGuards 데코레이터를 이용하여 토큰 요청이 필요한 API임을 알립니다.
  // UseGuards 안에는 2번 제목에서 선언했던 AuthGuard 클래스를 넣어줍니다.
  findAll(
    @Token(new ValidationPipe({ validateCustomDecorators: true })) token,
  ) {
    return response.status(HttpStatus.OK).json(token);
  }
}

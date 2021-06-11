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
  ValidationPipe,
} from '@nestjs/common';
import { Response } from 'express';
import { ApiDefaultResponse, ApiTags } from '@nestjs/swagger';
import { MemberService } from '../service/memberService';
import MemberDto from '../dto/memberDto';
import { AuthGuard } from 'src/auth/auth.gaurd';

@Controller('/api/member')
export class MemberController {
  private readonly logger = new Logger();
  constructor(private readonly memberService: MemberService) {}

  @Post('/signIn')
  @ApiTags('관리자 로그인')
  async signIn(@Body() memberDto: MemberDto, @Res() response: Response) {
    const existMember = this.memberService.signIn(memberDto);
    return response
      .status(HttpStatus.OK)
      .json((await existMember).toResponseObject(true));
  }

  @Get('/createManager')
  @ApiTags('최고 관리자 생성')
  createManager(@Query() memberDto: MemberDto, @Res() response: Response) {
    const msg = this.memberService.createManager(memberDto);
    return response.status(HttpStatus.OK).json(msg);
  }

  @Get('/validateMember')
  @ApiTags('관리자 아이디 확인')
  @UseGuards(new AuthGuard())
  async validateMember(@Req() memberDto: MemberDto, @Res() response: Response) {
    const member = await this.memberService.validateMember(memberDto);
    return response.status(HttpStatus.OK).json(member.toResponseObject());
  }
  // === 단위 테스트 중
  @Get('/findAll')
  @ApiTags('모든 유저의 정보 조회')
  @UseGuards(new AuthGuard())
  async findAll(@Res() response: Response) {
    const members = await this.memberService.findAll();
    console.log(members);
    return response.status(HttpStatus.OK).json(members);
  }
}

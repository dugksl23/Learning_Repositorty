import { IsString, IsNumber } from 'class-validator';
import bcrypt from 'bcrypt';

class MemberDto {
  @IsString()
  memberName: string;

  @IsString()
  password: string;

  @IsNumber()
  roles: number;
}

export default MemberDto;

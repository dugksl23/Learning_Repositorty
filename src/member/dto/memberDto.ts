import { IsString, IsNumber } from 'class-validator';
class MemberDto {
  @IsString()
  id: string;

  @IsString()
  password: string;

  @IsNumber()
  role: Array<number>;
}

export default MemberDto;

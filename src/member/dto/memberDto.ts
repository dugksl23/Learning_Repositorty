import { IsString, IsNumber } from 'class-validator';
import bcrypt from 'bcrypt';
import RoleEntity from '../entities/roleEntity';

class MemberDto {
  @IsString()
  memberName: string;

  @IsString()
  password: string;

  @IsNumber()
  roles: RoleEntity;
}

export default MemberDto;

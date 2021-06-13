import { IsString, IsNumber } from 'class-validator';
import RoleEntity from '../entities/roleEntity';
import MemberEntity from '../entities/memberEntity';
import { Entity } from 'typeorm';

class MemberDto {
  @IsString()
  memberName: string;

  @IsString()
  password: string;

  @IsNumber()
  roles: RoleEntity;

  setMemberName(memberName: string) {
    this.memberName = memberName;
    return this;
  }

  setPassword(password: string) {
    this.password = password;
    return this;
  }

  setRole(roles: RoleEntity) {
    this.roles = roles;
    return this;
  }
}

export default MemberDto;

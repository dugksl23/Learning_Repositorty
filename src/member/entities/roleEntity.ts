import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  ManyToOne,
} from 'typeorm';
import { BaseEntity } from 'typeorm/repository/BaseEntity';
import { MemberEntity } from './memberEntity';

@Entity('role')
class RoleEntity extends BaseEntity {
  @PrimaryGeneratedColumn('uuid') //auto-increment
  roleId: string;

  @Column()
  roleName: string;

  @ManyToOne(() => MemberEntity, (member) => member.roles)
  @JoinColumn() //{ name: 'id' }
  member: MemberEntity;
}

export default RoleEntity;

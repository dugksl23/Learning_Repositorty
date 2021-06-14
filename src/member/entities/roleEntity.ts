import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany,
} from 'typeorm';
import { BaseEntity } from 'typeorm/repository/BaseEntity';
import MemberEntity from './memberEntity';

@Entity('role')
class RoleEntity extends BaseEntity {
  @PrimaryGeneratedColumn('uuid') //auto-increment
  roleNo: number;

  @Column()
  roleName: string;

  @OneToMany(() => MemberEntity, (member) => member.roles)
  @JoinColumn({ name: 'id' })
  member: MemberEntity;
}

export default RoleEntity;

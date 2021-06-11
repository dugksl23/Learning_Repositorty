import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany,
  ManyToOne,
} from 'typeorm';
import { BaseEntity } from 'typeorm/repository/BaseEntity';

@Entity('role')
class RoleEntity extends BaseEntity {
  @PrimaryGeneratedColumn('uuid') //auto-increment
  @Column({ nullable: true })
  roleId: number;

  @Column()
  roleName: string;

  // @ManyToOne(() => MemberEntity, (member) => member.roles)
  // member: MemberEntity;
}

export default RoleEntity;

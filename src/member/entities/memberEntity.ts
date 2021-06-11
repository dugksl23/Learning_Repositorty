import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  UpdateDateColumn,
  OneToMany,
  BeforeInsert,
  BaseEntity,
  Transaction,
} from 'typeorm';
import { Length, IsDate, Min, Max } from 'class-validator';
import * as bcrypt from 'bcrypt';
import * as jwt from 'jsonwebtoken';
import RoleEntity from './roleEntity';

@Entity('member')
class MemberEntity extends BaseEntity {
  @PrimaryGeneratedColumn('uuid') //auto-increment
  @Length(6, 20)
  id: string;

  @Column()
  memberName: string;

  @Column({ type: 'date', default: () => new Date() })
  @CreateDateColumn()
  createdDate: Date;

  @UpdateDateColumn()
  updatedDate: Date;

  @Column()
  @Min(1)
  @Max(12)
  password: string;

  @Column({ type: 'date', default: () => new Date() })
  @IsDate()
  @CreateDateColumn()
  lastLoginDate: Date;

  @OneToMany((type) => RoleEntity, (role) => role.member)
  @JoinColumn({ name: 'roleId' })
  roles: RoleEntity[];

  @BeforeInsert()
  async hashPassword() {
    this.password = await bcrypt.hash(this.password, 12);
  }

  constructor(memberName: string, password: string) {
    super();
    this.memberName = memberName;
    this.password = password;
  }

  toResponseObject(showToken: boolean = true) {
    const { id, memberName, createdDate, lastLoginDate } = this;
    const responseObj = {
      id,
      memberName,
      createdDate,
      lastLoginDate,
    };

    if (showToken) {
      responseObj['token'] = this.token;
    }

    return responseObj;
  }

  private get token() {
    const { id, memberName } = this;
    return jwt.sign({ id, memberName }, process.env.SECRET, {
      expiresIn: '7d',
    });
  }
}

export default MemberEntity;

import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  UpdateDateColumn,
  OneToMany,
  JoinTable,
  BeforeInsert,
} from 'typeorm';
import { Length, IsDate, Min, Max } from 'class-validator';
import Role from './roleEntity';
import * as bcrypt from 'bcrypt';
import * as jwt from 'jsonwebtoken';
import RoleEntity from './roleEntity';

@Entity('member')
export class MemberEntity {
  @PrimaryGeneratedColumn('uuid') //auto-increment
  @Column({ nullable: true })
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

  @Column()
  @IsDate()
  lastLoginDate: Date;

  @OneToMany((type) => Role, (roles) => roles)
  @JoinColumn({ name: 'roleId' })
  roles: number;
  @BeforeInsert()
  async hashPassword() {
    this.password = await bcrypt.hash(this.password, 12);
  }

  constructor(memberName: string, password: string, roles: number) {
    this.memberName = memberName;
    this.password = password;
    this.roles = roles;
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

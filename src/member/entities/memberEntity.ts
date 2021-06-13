import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  UpdateDateColumn,
  BeforeInsert,
  BaseEntity,
  ManyToOne,
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

  @ManyToOne((type) => RoleEntity, (role) => role.roleNo)
  @JoinColumn({ name: 'roleNo' })
  roles: RoleEntity;

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
    const { memberName, createdDate, lastLoginDate } = this;
    const responseObj = {
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

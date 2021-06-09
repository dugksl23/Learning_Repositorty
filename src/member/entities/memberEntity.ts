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
import { response } from 'express';

@Entity()
export class MemberEntity {
  @PrimaryGeneratedColumn('uuid') //auto-increment
  @Column({ nullable: true })
  @Length(6, 20)
  private id: string;

  @Column()
  private memberName: string;

  @Column({ type: 'date', default: () => new Date() })
  @CreateDateColumn()
  private createdDate: Date;

  @UpdateDateColumn()
  private updatedDate: Date;

  @Column()
  @Min(1)
  @Max(12)
  private password: string;

  @Column()
  @IsDate()
  private lastLoginDate: Date;

  @OneToMany((type) => Role, (roles) => roles)
  @JoinColumn({ name: 'roleId' })
  roles: Array<Role>;

  @BeforeInsert()
  async hashPassword() {
    this.password = await bcrypt.hash(this.password, 12);
  }

  toResponseObject(showToken: boolean = true) {
    // module로 사용해주기 위해서 appModule에 등록.

    const { id, memberName, roles, createdDate, lastLoginDate, token } = this;
    const responseObj = {
      id,
      memberName,
      roles,
      createdDate,
      lastLoginDate,
      token,
    };

    if (showToken) {
      responseObj;
    }

    return responseObj;
  }

  async comparePassword(oriPw: string) {
    return await bcrypt.compare(oriPw, this.password);
  }

  private get token() {
    const { id, memberName } = this;
    return jwt.sign({ id, memberName }, process.env.SECRET, {
      expiresIn: '7d',
    });
  }
}

export default MemberEntity;

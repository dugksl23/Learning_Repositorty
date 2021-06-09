import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  UpdateDateColumn,
  OneToMany,
  JoinTable,
} from 'typeorm';
import { Length, IsDate, Min, Max } from 'class-validator';
import Role from './roleEntity';

@Entity()
export class Member {
  @PrimaryGeneratedColumn() //auto-increment
  @Column({ nullable: true })
  @Length(6, 20)
  private id: string;

  @Column({ type: 'date', default: () => 'new data()' })
  @CreateDateColumn()
  private createdDate: Date;

  @UpdateDateColumn()
  private updateDate: Date;

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
}

export default Member;

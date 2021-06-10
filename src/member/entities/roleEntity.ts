import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany,
} from 'typeorm';

@Entity('role')
class RoleEntity {
  @PrimaryGeneratedColumn() //auto-increment
  @Column({ nullable: true })
  private roleId: string;

  @Column()
  private roleName: number;
}

export default RoleEntity;

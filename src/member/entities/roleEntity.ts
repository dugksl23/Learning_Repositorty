import {
  JoinColumn,
  Entity,
  Column,
  PrimaryGeneratedColumn,
  OneToMany,
} from 'typeorm';
import Permission from './permissionEntity';

@Entity()
class RoleEntity {
  @PrimaryGeneratedColumn() //auto-increment
  @Column({ nullable: true })
  private roleId: string;

  @Column()
  private roleName: number;

  @Column()
  @OneToMany((type) => Permission, (permissions) => permissions)
  private permissions: Array<Permission>;
}

export default RoleEntity;

import { JoinColumn, Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
class PermissionEntity {
  @PrimaryGeneratedColumn() //auto-increment
  @Column({ nullable: true })
  private permissionId: string;

  @Column()
  @Column({ nullable: true })
  private permissionName: string;

  @Column()
  @Column({ nullable: true })
  private url: string;
}

export default PermissionEntity;

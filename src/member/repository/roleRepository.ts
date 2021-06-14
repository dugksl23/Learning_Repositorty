import { EntityRepository, Repository } from 'typeorm';
import RoleEntity from '../entities/roleEntity';

@EntityRepository(RoleEntity)
export class RoleRepository extends Repository<RoleEntity> {
  async findRole(roleNo) {
    return await this.createQueryBuilder('role')
      .where('role.roleNo = :roleNo', { roleNo: roleNo })
      .getOne();
  }

  async createRole() {
    return await this.createQueryBuilder()
      .insert()
      .into(RoleEntity)
      .values([
        {
          roleNo: Number(process.env.roleNo),
          roleName: process.env.roleName,
        },
      ])
      .execute();
  }
}

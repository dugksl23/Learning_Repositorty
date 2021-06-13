import { HttpException, HttpStatus, Injectable, Res } from '@nestjs/common';
import { RoleRepository } from '../repository/roleRepository';

import { Connection } from 'typeorm';

@Injectable()
export class RoleService {
  private roleRepository: RoleRepository;
  constructor(private readonly connection: Connection) {
    this.roleRepository = this.connection.getCustomRepository(RoleRepository);
  }

  async findRole(roleNo: number) {
    return await this.roleRepository.findRole(roleNo);
  }

  async createRole() {
    return await this.roleRepository.createRole();
  }
}

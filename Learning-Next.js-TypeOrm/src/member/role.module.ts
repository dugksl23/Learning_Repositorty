import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import RoleEntity from './entities/RoleEntity';
import { RoleRepository } from './repository/roleRepository';
import { RoleService } from './service/roleService';

@Module({
  imports: [TypeOrmModule.forFeature([RoleEntity])],
  controllers: [],
  providers: [RoleService, RoleRepository],
  exports: [RoleRepository],
})
export class RoleModule {}

import { EntityRepository, Repository } from 'typeorm';
import MemberEntity from '../entities/memberEntity';
import MemberDto from '../dto/memberDto';

@EntityRepository(MemberEntity)
export class MemberRepository extends Repository<MemberEntity> {
  signIn(memberDto: MemberDto) {
    const { memberName, password } = memberDto;
    console.log(memberName, '++', password);
    return this.createQueryBuilder('member')
      .where('member.memberName = :memberName', { memberName })
      .andWhere('member.password = :password', { password: password })
      .getOne();
  }

  async findByMemberName(memberDto: MemberDto) {
    const { memberName } = memberDto;
    console.log(memberName);
    return await this.createQueryBuilder('member')
      .where('member.memberName = :memberName', { memberName: memberName })
      .getOne();
  }

  async createMember(memberEntity: MemberEntity) {
    const member = this.create(memberEntity);
    return await this.save(member);
  }

  async findAll() {
    return await this.createQueryBuilder('member').getMany();
  }

  async existManager(memberName: string) {
    return await this.createQueryBuilder('member')
      .where('member.memberName = :memberName', { memberName: memberName })
      .getOne();
  }
}

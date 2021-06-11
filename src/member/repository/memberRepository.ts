import { EntityRepository, Repository } from 'typeorm';
import MemberEntity from '../entities/memberEntity';
import MemberDto from '../dto/memberDto';

@EntityRepository(MemberEntity)
export class MemberRepository extends Repository<MemberEntity> {
  signIn(memberDto: MemberDto) {
    const { memberName, password } = memberDto;
    return this.createQueryBuilder('member')
      .where('id = (id)', { memberName })
      .andWhere('password = (password)', { password })
      .getOne();
  }

  async findByMemberName(memberDto: MemberDto) {
    const { memberName } = memberDto;

    return await this.createQueryBuilder('member')
      .where('memberName = (memberName)', { memberName })
      .getOne();
  }

  async createMember(memberEntity: MemberEntity) {
    const member = this.create(memberEntity);
    return await this.save(member);
  }

  async findAll() {
    return await this.createQueryBuilder('member').getMany();
  }

  async existManager(name: string) {
    console.log(name);
    return await this.createQueryBuilder()
      .where('membername = (membername)', { name })
      .getOne();
  }
}

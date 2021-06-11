import { EntityRepository, Repository } from 'typeorm';
import MemberEntity from '../entities/memberEntity';
import MemberDto from '../dto/memberDto';

var dummyMember = {
  id: '001',
  memberName: 'root',
  password: 'root',
  createdDate: new Date(),
  updatedDate: new Date(),
  lastLoginDate: new Date(),
};

@EntityRepository(MemberEntity)
export class MemberRepository extends Repository<MemberEntity> {
  signIn(memberDto: MemberDto) {
    const { memberName, password } = memberDto;

    dummyMember.memberName === memberName && dummyMember.password === password
      ? dummyMember
      : dummyMember;

    return this.createQueryBuilder('member')
      .where('id = (id)', {})
      .andWhere('password = (password)', { password })
      .getOne();
  }

  findByMemberName(memberDto: MemberDto): string {
    const { memberName } = memberDto;

    return dummyMember.memberName === memberName
      ? dummyMember.memberName
      : null;
  }

  async createMember(memberEntity: MemberEntity) {
    const member = this.create(memberEntity);
    console.log(member);
    return await this.save(member);
  }

  findAll() {
    return this.findAll();
  }
}

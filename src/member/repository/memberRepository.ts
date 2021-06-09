import { EntityRepository, Repository } from 'typeorm';
import MemberEntity from '../entities/memberEntity';
import MemberDto from '../dto/memberDto';

var dummyMember = {
  id: 'root',
  password: 'root',
  role: 1,
};

@EntityRepository()
export class MemberRepository extends Repository<MemberEntity> {
  findMember(memberDto: MemberDto) {
    const id = memberDto.id;
    const password = memberDto.password;

    return dummyMember.id === id && dummyMember.password === password
      ? dummyMember
      : '아이디 비번 다시 확인';

    // this.createQueryBuilder('member')
    //   .where('id = (id)', { id })
    //   .andWhere('password = (password)', { password })
    //   .getOne();
  }
}

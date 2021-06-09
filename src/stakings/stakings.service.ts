import { Injectable } from '@nestjs/common';
import { CreateStakingDto } from './dto/create-staking.dto';
import { UpdateStakingDto } from './dto/update-staking.dto';

const dummydata={
	periodstart:'2021-06-08 00:00:00'
	, periodend:'2021-06-09 00:00:00'
	, periodinhours: 24			
	, convertedamount: '0.9'
	, countstakers: 55
	, stakedamount: '1038.7'
	, payoutrate: '0.95'
	, payoutamount: '0.855'
	, feeatdestunit: 'ETH'
	, boostfactor: '15.9'
	, } 
@Injectable()
export class StakingsService {
  create(createStakingDto: CreateStakingDto) {
    return 'This action adds a new staking';
  }
	findAll() {//    return `This action returns all stakings`;
		return {status:'OK', list:[dummydata]}
  }

	findalloffsetlimit(offset:number, limit:number){
		return {status:'OK', list:Array(limit).fill(dummydata)}
	}
	search( keyword:string, offset:number , limit:number){
		return {status:'OK', list:Array(limit).fill(dummydata)} // 
	}

  findOne(id: number) {
    return `This action returns a #${id} staking`;
  }

  update(id: number, updateStakingDto: UpdateStakingDto) {
    return `This action updates a #${id} staking`;
  }

  remove(id: number) {
    return `This action removes a #${id} staking`;
  }
}

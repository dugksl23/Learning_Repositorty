import {
  Injectable,
  CanActivate,
  ExecutionContext,
  HttpException,
  HttpStatus,
} from '@nestjs/common';
import * as jwt from 'jsonwebtoken';

@Injectable()
export class AuthGuard implements CanActivate {
  async canActivate(context: ExecutionContext): Promise<boolean> {
    const request = context.switchToHttp().getRequest();
    // 클라이언트에서 보낸 request의 정보를 가져옵니다.
    if (request.headers.athorization === null) {
      return false;
    }

    request.user = await this.validationToken(request.headers.authorization);
    console.log(request.user);
    return true;
  }

  async validationToken(auth: string) {
    if (auth.split(' ')[0] !== 'Bearer') {
      throw new HttpException(
        `{ "body" : "Invalid token", "code" : "401"}`,
        HttpStatus.FORBIDDEN,
      );
    }

    const token = auth.split(' ')[1];
    try {
      return jwt.verify(token, process.env.SECRET);
    } catch (err) {
      const errMsg = 'Token error : ' + (err.message || err.name);
      throw new HttpException(errMsg, HttpStatus.FORBIDDEN);
    }
  }
}

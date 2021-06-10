import {
  Injectable,
  CanActivate,
  ExecutionContext,
  HttpException,
  HttpStatus,
  UnauthorizedException,
} from '@nestjs/common';
import * as jwt from 'jsonwebtoken';

@Injectable()
export class AuthGuard implements CanActivate {
  async canActivate(context: ExecutionContext): Promise<boolean> {
    const request = context.switchToHttp().getRequest();
    if (request.headers.athorization === null) {
      return false;
    }
    request.user = await this.validationToken(request.headers.authorization);

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
      console.log(token);
      return jwt.verify(token, process.env.SECRET);
    } catch (err) {
      const errMsg = 'Token error : ' + (err.message || err.name);
      throw new UnauthorizedException('Invalid Token used');
    }
  }
}

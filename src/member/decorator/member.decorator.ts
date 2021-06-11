import { createParamDecorator, ExecutionContext } from '@nestjs/common';

export const Token = createParamDecorator((data, ctx: ExecutionContext) => {
  const request = ctx.switchToHttp().getRequest();
  console.log(request.token);
  return request.token;
});

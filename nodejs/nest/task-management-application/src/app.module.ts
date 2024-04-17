import { Module } from '@nestjs/common';
import { TasksModule } from './tasks/tasks.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { AuthModule } from './auth/auth.module';

@Module({
  imports: [
    TasksModule,
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      inject: [ConfigService],
      useFactory: (config: ConfigService) => ({
        type: 'postgres',
        host: 'localhost',
        port: 5432,
        username: 'postgres',
        password: config.get<string>('POSTGRES_PASSWORD'),
        database: 'task-management',
        autoLoadEntities: true,
        synchronize: true,
      }),
    }),
    AuthModule,
  ],
})
export class AppModule {}

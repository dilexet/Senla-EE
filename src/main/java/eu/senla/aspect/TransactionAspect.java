package eu.senla.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class TransactionAspect {

    private final Connection connection;

    public TransactionAspect(Connection connection) {
        this.connection = connection;
    }

    @Around("@within(eu.senla.annotation.Transaction) || @annotation(eu.senla.annotation.Transaction)")
    public Object runInTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            connection.setAutoCommit(false);
            Object jp = joinPoint.proceed();
            connection.commit();
            return jp;
        } catch (RuntimeException e) {
            connection.rollback();
            throw e;
        } catch (Exception e) {
            connection.commit();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}

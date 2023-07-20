package com.joypay.config;

import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.dialect.MySqlDialect;
import org.babyfish.jimmer.sql.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.function.Function;

/**
 * @author dcj
 * @Date 2023/7/18 17:27
 * 演示:
 */
@Configuration
public class SqlClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlClientConfig.class);
    @Bean
    public JSqlClient sqlClient(DataSource dataSource, @Value("${spring.datasource.url}") String jdbcUrl) {
        return JSqlClient.newBuilder()
                .setConnectionManager(
                        /*
                         * It's very important to use
                         *      "org.springframework.jdbc.datasource.DataSourceUtils"!
                         * This is spring transaction aware ConnectionManager
                         */
                        new ConnectionManager() {
                            @Override
                            public <R> R execute(Function<Connection, R> block) {
                                //让spring接管事务
                                Connection con = DataSourceUtils.getConnection(dataSource);
                                try {
                                    return block.apply(con);
                                } finally {
                                    DataSourceUtils.releaseConnection(con, dataSource);
                                }
                            }
                        }
                )
                .setDialect(new MySqlDialect())//设置方言
                .setExecutor(new Executor() {
                    @Override
                    public <R> R execute(Connection con, String sql, List<Object> variables, ExecutionPurpose purpose,
                                         StatementFactory statementFactory, SqlFunction<PreparedStatement, R> block) {
                        LOGGER.info("Execute sql : \"{}\", " +
                                "with variables: {}", sql, variables);
                        return DefaultExecutor.INSTANCE.execute(con, sql, variables,
                                purpose, statementFactory, block);
                    }
                   }
                )
                .build();
    }
}

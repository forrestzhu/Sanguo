package com.jayzero.games.sanguo.launcher;

import com.jayzero.games.sanguo.rest.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * SanguoLauncher
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
@SpringBootApplication(scanBasePackages = "com.jayzero.games.sanguo")
@Import(value = RestConfiguration.class)
public class SanguoLauncher {

    public static void main(String[] args) {
        SpringApplication.run(SanguoLauncher.class, args);
    }
}

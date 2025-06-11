package org.jit.sose.redis;

import org.jit.sose.TestAppDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetTest extends TestAppDocument {

	@Autowired
	RedisService redisService;

	// 判断集合中是否存在元素
	@Test
	public void sHasKey() {
//		String key = "ROLE_ADMIN";
		String key = "1";
		String value = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJoDeo7KrSNY0XPDzTjHRjVCd00iBPmdqaX+8BWSjzlKt30wdJrF5h2Ihp6b59CA4hErsHyncikjFU+NVmY59/OHjepnyxr1GjIn1vHthKZ/yfUcYLjWhkXoUcUC0l1xXNh/pMfYcX3PDq9xnRCflaJZLpBHt2Mu9iAaDugiYGrbAgMBAAECgYBtCyN98A/v49XAMhSeK7EQynNvYDPHjBJuXfccPv064iTR9TnJz24i4OP3bokNvgLRh25Yg7G3YIiZH4fZrxdW8vWMp077tt04E/pgz5WeVzBbEzBu72ISx5HI98JjXPLop9w/VCTXGRrGIAGifkAAO7mhrtOxifkiBkzbzTS24QJBAM42YhspIQfoaHMj9MB69Nh5KdnGHmbSISVvYX+R9WgZ7goal1wZYu7TL/cHjHX4mkhLSlGk/QeJOfuTwtSpJUsCQQC/MseMZWdWYmIPYxopwLgqMzFzaT2qvKIwgw+YIcZPl5hHAN9o0XEUzN4LJR/Lsr6GNGI+trgQI7E1HOg9XaaxAkBah/2iNvhNZHcWtZ5qMpDzyJ3bAeOu+Gmc6b1AKHA498lNnkb9JBgPCaBxL1s3H6F8Q7GpDmoXuOM/06shefDpAkEApEYI1UO6yam1/upy7DS9BuT9M9/UMAHqiCvTFi7OqEvjdf319aUdt3Vdwc2fx/BHah9P2fo7owJHmw6/KxpvwQJBAJfnm7wrA7624T8FO6rkoi40umO1NbyzgMkzZ34fmft6v5+ZuxVpyRRnm6Khn9/nLDJyy6YB58u9W/n8iXLvof0=";
		boolean b = redisService.sHasKey(key, value);
		System.out.println(b);
	}

}

#include <stdio.h>
#include <stdlib.h>

main() {
       int i = -3;
       long l = 213215;
       short s = 12;
       char c = 'a';
       float f = 0.12;
       double d = 0.12331512132;
       char arr[] = "afdsfa";
       printf("%d\n", i);
       printf("%ld\n", l);
       printf("%hd\n", s);
       printf("%c\n", c);
       printf("%f\n", f);
       printf("%lf\n", d);
       printf("%u\n", i);
       printf("%#x\n", l);
       printf("%#o\n", l);
       printf("%s\n", arr);
       system("pause");
}

.section .text
 .global calculateEstimated;
 calculateEstimated:
   # prologue
   pushl %ebp # save previous stack frame pointer
   movl %esp, %ebp # the stack frame pointer for sum function
   pushl %ebx
   
   movl 8(%ebp), %eax		
   movl 12(%ebp), %ecx
   movl 16(%ebp), %ebx
   subl %ecx, %eax
   movl %ebx, %ecx
   movl $0, %edx
   cdq
   idivl %ecx  
   
   # epilogue
   popl %ebx
   movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
   popl %ebp # restore the previous stack frame pointer
   ret
   

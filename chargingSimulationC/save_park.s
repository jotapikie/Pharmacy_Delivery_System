.section .data
 .equ POWER_OUTPUT , 12
 .equ DATA_SIZE, 16
 .equ ID_OFFSET, 0 
 .equ OCCUPANCY_OFFSET, 4
 .equ POWER_OUTPUT_OFFSET, 8
 .equ ORIGINAL_PARK_OUTPUT_OFFSET, 12
.section .text
 .global save_park;
 save_park:
   # prologue
   pushl %ebp # save previous stack frame pointer
   movl %esp, %ebp # the stack frame pointer for sum function
   pushl %ebx
   pushl %esi
   
   movl 8(%ebp), %esi		
   movl 12(%ebp), %ecx
   movl 16(%ebp), %ebx
   movl $0, %edx
   cmpl $0, %ecx
   je new_park
   
   start_loop:
   cmpl %ebx, (%esi)
   je found
   addl $1, %edx
   addl $DATA_SIZE, %esi
   loop start_loop
   
   new_park:
   movl %ebx, (%esi)
   movl $1, OCCUPANCY_OFFSET(%esi)
   movl $POWER_OUTPUT, POWER_OUTPUT_OFFSET(%esi)
   movl $POWER_OUTPUT, ORIGINAL_PARK_OUTPUT_OFFSET(%esi)
   movl $2, %eax
   jmp end
   
   
   
   end:
   # epilogue
   popl %ebx
   movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
   popl %ebp # restore the previous stack frame pointer
   ret
   
   
   found:
   pushl %eax
   pushl %edx
   pushl %ecx
   movl $POWER_OUTPUT, %eax
   addl $1, OCCUPANCY_OFFSET(%esi)
   movl OCCUPANCY_OFFSET(%esi), %ecx
   cdq
   div %ecx
   movl %eax, POWER_OUTPUT_OFFSET(%esi)
   movl $1, %edx
   popl %ecx
   popl %edx
   popl %eax
   jmp end
   
   
   
   

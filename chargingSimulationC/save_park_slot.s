.section .data
 .equ PARK_SLOT_DATA_SIZE, 16
 .equ SLOT_ID_OFFSET, 0
 .equ VEHICLE_ID_OFFSET, 4
 .equ BATTERY_OFFSET, 8
 .equ ACTUAL_BATTERY_OFFSET, 12
 .equ OCCUPANCY_OFFSET, 4
 .equ SLOTS_OFFSET, 16

.section .text
 .global save_park_slot
 
save_park_slot:
 #prologue
 pushl %ebp
 movl %esp, %ebp
 pushl %esi
 pushl %edi
 
 movl 8(%ebp), %esi
 movl OCCUPANCY_OFFSET(%esi), %eax
 subl $1, %eax
 movl SLOTS_OFFSET(%esi), %edi
 movl $PARK_SLOT_DATA_SIZE, %ecx
 mull %ecx
 addl %eax, %edi
 
 movl 12(%ebp), %eax                    
 movl %eax, SLOT_ID_OFFSET(%edi)       
 movl 16(%ebp), %eax                   
 movl %eax, VEHICLE_ID_OFFSET(%edi)
 movl 20(%ebp), %eax                 
 movl %eax, BATTERY_OFFSET(%edi)         
 movl 24(%ebp), %eax                 
 movl %eax, ACTUAL_BATTERY_OFFSET(%edi)       

  # epilogue
  popl %edi
  popl %esi
  movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
  popl %ebp

  ret

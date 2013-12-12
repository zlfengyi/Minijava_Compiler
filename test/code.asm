.data
.align 0
endl: .asciiz "\n"

.data
.align 0
error: .asciiz "ERROR: abnormal termination"

.text
.globl main
main:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 12
li $a0, 4
li $v0, 9
syscall
move $t0, $v0
li $a0, 4
li $v0, 9
syscall
move $t1, $v0
la $t2, BT_Start
sw $t2, 0($t0)
sw $t0, 0($t1)
move $t0, $t1
lw $t1, 0($t0)
lw $t2, 0($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $a0, $t1
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 12
jr $ra

.text
.globl BT_Start
BT_Start:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
li $a0, 80
li $v0, 9
syscall
move $t0, $v0
li $a0, 28
li $v0, 9
syscall
move $t1, $v0
la $t2, Tree_RecPrint
sw $t2, 76($t0)
la $t2, Tree_Print
sw $t2, 72($t0)
la $t2, Tree_Search
sw $t2, 68($t0)
la $t2, Tree_RemoveLeft
sw $t2, 64($t0)
la $t2, Tree_RemoveRight
sw $t2, 60($t0)
la $t2, Tree_Remove
sw $t2, 56($t0)
la $t2, Tree_Delete
sw $t2, 52($t0)
la $t2, Tree_Insert
sw $t2, 48($t0)
la $t2, Tree_Compare
sw $t2, 44($t0)
la $t2, Tree_SetHas_Right
sw $t2, 40($t0)
la $t2, Tree_SetHas_Left
sw $t2, 36($t0)
la $t2, Tree_GetHas_Left
sw $t2, 32($t0)
la $t2, Tree_GetHas_Right
sw $t2, 28($t0)
la $t2, Tree_SetKey
sw $t2, 24($t0)
la $t2, Tree_GetKey
sw $t2, 20($t0)
la $t2, Tree_GetLeft
sw $t2, 16($t0)
la $t2, Tree_GetRight
sw $t2, 12($t0)
la $t2, Tree_SetLeft
sw $t2, 8($t0)
la $t2, Tree_SetRight
sw $t2, 4($t0)
la $t2, Tree_Init
sw $t2, 0($t0)
li $t2, 4
L2: 
nop
li $t3, 28
move $v1, $t3
slt $v0, $t2, $v1
move $t4, $v0
beqz $t4 L3
move $v1, $t2
add $v0, $t1, $v1
move $t3, $v0
li $t4, 0
sw $t4, 0($t3)
li $v1, 4
add $v0, $t2, $v1
move $t2, $v0
j L2
L3: 
nop
sw $t0, 0($t1)
move $s1, $t1
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 0($t1)
li $t1, 16
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 72($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
li $t0, 100000000
move $a0, $t0
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 8
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 72($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 24
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 4
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 12
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 20
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 28
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 48($t1)
li $t1, 14
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 72($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 68($t1)
li $t1, 24
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $a0, $t3
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 68($t1)
li $t1, 12
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $a0, $t3
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 68($t1)
li $t1, 16
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $a0, $t3
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 68($t1)
li $t1, 50
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $a0, $t3
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 68($t1)
li $t1, 12
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $a0, $t3
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 52($t1)
li $t1, 12
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 72($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 68($t1)
li $t1, 12
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $a0, $t3
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
li $t0, 0
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_Init
Tree_Init:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
move $s1, $a1
sw $s1, 12($s0)
li $t0, 0
sw $t0, 16($s0)
li $t0, 0
sw $t0, 20($s0)
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_SetRight
Tree_SetRight:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
move $s1, $a1
sw $s1, 8($s0)
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_SetLeft
Tree_SetLeft:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
move $s1, $a1
sw $s1, 4($s0)
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_GetRight
Tree_GetRight:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 16
sw $s0, -12($fp)
move $s0, $a0
lw $t0, 8($s0)
move $v0, $t0
lw $s0, -12($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 16
jr $ra

.text
.globl Tree_GetLeft
Tree_GetLeft:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 16
sw $s0, -12($fp)
move $s0, $a0
lw $t0, 4($s0)
move $v0, $t0
lw $s0, -12($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 16
jr $ra

.text
.globl Tree_GetKey
Tree_GetKey:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 16
sw $s0, -12($fp)
move $s0, $a0
lw $t0, 12($s0)
move $v0, $t0
lw $s0, -12($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 16
jr $ra

.text
.globl Tree_SetKey
Tree_SetKey:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
move $s1, $a1
sw $s1, 12($s0)
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_GetHas_Right
Tree_GetHas_Right:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 16
sw $s0, -12($fp)
move $s0, $a0
lw $t0, 20($s0)
move $v0, $t0
lw $s0, -12($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 16
jr $ra

.text
.globl Tree_GetHas_Left
Tree_GetHas_Left:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 16
sw $s0, -12($fp)
move $s0, $a0
lw $t0, 16($s0)
move $v0, $t0
lw $s0, -12($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 16
jr $ra

.text
.globl Tree_SetHas_Left
Tree_SetHas_Left:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
move $s1, $a1
sw $s1, 16($s0)
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_SetHas_Right
Tree_SetHas_Right:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 20
sw $s0, -12($fp)
sw $s1, -16($fp)
move $s0, $a0
move $s1, $a1
sw $s1, 20($s0)
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 20
jr $ra

.text
.globl Tree_Compare
Tree_Compare:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 24
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
move $s0, $a0
move $s1, $a1
move $s2, $a2
li $t1, 0
li $v1, 1
add $v0, $s2, $v1
move $t0, $v0
move $v1, $s2
slt $v0, $s1, $v1
move $t1, $v0
beqz $t1 L4
li $t1, 0
j L5
L4: 
nop
li $t2, 1
move $v1, $t0
slt $v0, $s1, $v1
move $t3, $v0
move $v1, $t3
sub $v0, $t2, $v1
move $t0, $v0
beqz $t0 L6
li $t1, 0
j L7
L6: 
nop
li $t1, 1
L7: 
nop
L5: 
nop
move $v0, $t1
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 24
jr $ra

.text
.globl Tree_Insert
Tree_Insert:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 32
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
sw $s4, -28($fp)
move $s0, $a0
move $s1, $a1
li $a0, 80
li $v0, 9
syscall
move $t0, $v0
li $a0, 28
li $v0, 9
syscall
move $t1, $v0
la $t2, Tree_RecPrint
sw $t2, 76($t0)
la $t2, Tree_Print
sw $t2, 72($t0)
la $t2, Tree_Search
sw $t2, 68($t0)
la $t2, Tree_RemoveLeft
sw $t2, 64($t0)
la $t2, Tree_RemoveRight
sw $t2, 60($t0)
la $t2, Tree_Remove
sw $t2, 56($t0)
la $t2, Tree_Delete
sw $t2, 52($t0)
la $t2, Tree_Insert
sw $t2, 48($t0)
la $t2, Tree_Compare
sw $t2, 44($t0)
la $t2, Tree_SetHas_Right
sw $t2, 40($t0)
la $t2, Tree_SetHas_Left
sw $t2, 36($t0)
la $t2, Tree_GetHas_Left
sw $t2, 32($t0)
la $t2, Tree_GetHas_Right
sw $t2, 28($t0)
la $t2, Tree_SetKey
sw $t2, 24($t0)
la $t2, Tree_GetKey
sw $t2, 20($t0)
la $t2, Tree_GetLeft
sw $t2, 16($t0)
la $t2, Tree_GetRight
sw $t2, 12($t0)
la $t2, Tree_SetLeft
sw $t2, 8($t0)
la $t2, Tree_SetRight
sw $t2, 4($t0)
la $t2, Tree_Init
sw $t2, 0($t0)
li $t2, 4
L8: 
nop
li $t3, 28
move $v1, $t3
slt $v0, $t2, $v1
move $t4, $v0
beqz $t4 L9
move $v1, $t2
add $v0, $t1, $v1
move $t3, $v0
li $t4, 0
sw $t4, 0($t3)
li $v1, 4
add $v0, $t2, $v1
move $t2, $v0
j L8
L9: 
nop
sw $t0, 0($t1)
move $s2, $t1
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 0($t1)
move $a0, $t0
move $a1, $s1
jalr $t2
move $t1, $v0
move $s3, $s0
li $s4, 1
L10: 
nop
beqz $s4 L11
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $t1
move $v1, $t0
slt $v0, $s1, $v1
move $t1, $v0
beqz $t1 L12
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L14
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s3, $t1
j L15
L14: 
nop
li $s4, 0
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 36($t1)
li $t1, 1
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 8($t1)
move $a0, $t0
move $a1, $s2
jalr $t2
move $t1, $v0
L15: 
nop
j L13
L12: 
nop
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L16
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 12($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s3, $t1
j L17
L16: 
nop
li $s4, 0
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 40($t1)
li $t1, 1
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s3
lw $t1, 0($t0)
lw $t2, 4($t1)
move $a0, $t0
move $a1, $s2
jalr $t2
move $t1, $v0
L17: 
nop
L13: 
nop
j L10
L11: 
nop
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $s4, -28($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 32
jr $ra

.text
.globl Tree_Delete
Tree_Delete:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 52
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
sw $s4, -28($fp)
sw $s6, -36($fp)
sw $s7, -40($fp)
sw $s5, -44($fp)
move $s0, $a0
move $s1, $a1
move $s2, $s0
move $s3, $s0
li $s4, 1
li $v1, 0
sw $v1, -32($fp)
li $s6, 1
L18: 
nop
beqz $s4 L19
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s7, $t1
move $v1, $s7
slt $v0, $s1, $v1
move $t0, $v0
beqz $t0 L20
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L22
move $s3, $s2
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s2, $t1
j L23
L22: 
nop
li $s4, 0
L23: 
nop
j L21
L20: 
nop
move $v1, $s1
slt $v0, $s7, $v1
move $t0, $v0
beqz $t0 L24
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L26
move $s3, $s2
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 12($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s2, $t1
j L27
L26: 
nop
li $s4, 0
L27: 
nop
j L25
L24: 
nop
beqz $s6 L28
li $s7, 0
li $s5, 1
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $v1, $t1
sub $v0, $s5, $v1
move $t0, $v0
beqz $t0 L32
li $s5, 1
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $v1, $t1
sub $v0, $s5, $v1
move $t0, $v0
beqz $t0 L32
li $s7, 1
L32: 
nop
beqz $s7 L30
j L31
L30: 
nop
move $t0, $s0
lw $t1, 0($t0)
lw $t2, 56($t1)
move $a0, $t0
move $a1, $s3
move $a2, $s2
jalr $t2
move $t1, $v0
L31: 
nop
j L29
L28: 
nop
move $t0, $s0
lw $t1, 0($t0)
lw $t2, 56($t1)
move $a0, $t0
move $a1, $s3
move $a2, $s2
jalr $t2
move $t1, $v0
L29: 
nop
lw $v1, -32($fp)
li $v1, 1
sw $v1, -48($fp)
li $s4, 0
L25: 
nop
L21: 
nop
li $s6, 0
j L18
L19: 
nop
lw $v1, -48($fp)
move $v0, $v1
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $s4, -28($fp)
lw $s5, -44($fp)
lw $s6, -36($fp)
lw $s7, -40($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 52
jr $ra

.text
.globl Tree_Remove
Tree_Remove:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 28
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
move $s0, $a0
move $s1, $a1
move $s2, $a2
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L33
move $t0, $s0
lw $t1, 0($t0)
lw $t2, 64($t1)
move $a0, $t0
move $a1, $s1
move $a2, $s2
jalr $t2
move $t1, $v0
j L34
L33: 
nop
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L35
move $t0, $s0
lw $t1, 0($t0)
lw $t2, 60($t1)
move $a0, $t0
move $a1, $s1
move $a2, $s2
jalr $t2
move $t1, $v0
j L36
L35: 
nop
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s3, $t1
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $t1
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $t1
move $t1, $s0
lw $t2, 0($t1)
lw $t3, 44($t2)
move $a0, $t1
move $a1, $s3
move $a2, $t0
jalr $t3
move $t2, $v0
beqz $t2 L37
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 8($t1)
lw $t1, 24($s0)
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 36($t1)
li $t1, 0
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
j L38
L37: 
nop
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 4($t1)
lw $t1, 24($s0)
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 40($t1)
li $t1, 0
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
L38: 
nop
L36: 
nop
L34: 
nop
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 28
jr $ra

.text
.globl Tree_RemoveRight
Tree_RemoveRight:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 32
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
sw $s4, -28($fp)
move $s0, $a0
move $s1, $a1
move $s2, $a2
L39: 
nop
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L40
move $s3, $s2
lw $t0, 0($s3)
lw $s4, 24($t0)
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 12($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $t1
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $a0, $s3
move $a1, $t1
jalr $s4
move $t0, $v0
move $s1, $s2
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 12($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s2, $t1
j L39
L40: 
nop
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 4($t1)
lw $t1, 24($s0)
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 40($t1)
li $t1, 0
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $s4, -28($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 32
jr $ra

.text
.globl Tree_RemoveLeft
Tree_RemoveLeft:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 32
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
sw $s4, -28($fp)
move $s0, $a0
move $s1, $a1
move $s2, $a2
L41: 
nop
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L42
move $s3, $s2
lw $t0, 0($s3)
lw $s4, 24($t0)
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $t0, $t1
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $a0, $s3
move $a1, $t1
jalr $s4
move $t0, $v0
move $s1, $s2
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s2, $t1
j L41
L42: 
nop
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 8($t1)
lw $t1, 24($s0)
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 36($t1)
li $t1, 0
move $a0, $t0
move $a1, $t1
jalr $t2
move $t3, $v0
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $s4, -28($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 32
jr $ra

.text
.globl Tree_Search
Tree_Search:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 36
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
sw $s4, -28($fp)
sw $s5, -32($fp)
move $s0, $a0
move $s1, $a1
move $s2, $s0
li $s3, 1
li $s4, 0
L43: 
nop
beqz $s3 L44
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s5, $t1
move $v1, $s5
slt $v0, $s1, $v1
move $t0, $v0
beqz $t0 L45
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L47
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s2, $t1
j L48
L47: 
nop
li $s3, 0
L48: 
nop
j L46
L45: 
nop
move $v1, $s1
slt $v0, $s5, $v1
move $t0, $v0
beqz $t0 L49
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L51
move $t0, $s2
lw $t1, 0($t0)
lw $t2, 12($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $s2, $t1
j L52
L51: 
nop
li $s3, 0
L52: 
nop
j L50
L49: 
nop
li $s4, 1
li $s3, 0
L50: 
nop
L46: 
nop
j L43
L44: 
nop
move $v0, $s4
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $s4, -28($fp)
lw $s5, -32($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 36
jr $ra

.text
.globl Tree_Print
Tree_Print:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 16
sw $s0, -12($fp)
move $s0, $a0
move $t0, $s0
move $t1, $s0
lw $t2, 0($t1)
lw $t3, 76($t2)
move $a0, $t1
move $a1, $t0
jalr $t3
move $t2, $v0
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 16
jr $ra

.text
.globl Tree_RecPrint
Tree_RecPrint:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 28
sw $s0, -12($fp)
sw $s1, -16($fp)
sw $s2, -20($fp)
sw $s3, -24($fp)
move $s0, $a0
move $s1, $a1
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 32($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L53
move $s2, $s0
lw $t0, 0($s2)
lw $s3, 76($t0)
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 16($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $a0, $s2
move $a1, $t1
jalr $s3
move $t0, $v0
j L54
L53: 
nop
L54: 
nop
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 20($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $a0, $t1
li $v0, 1
syscall
la $a0, endl
li $v0, 4
syscall
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 28($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
beqz $t1 L55
move $s2, $s0
lw $t0, 0($s2)
lw $s3, 76($t0)
move $t0, $s1
lw $t1, 0($t0)
lw $t2, 12($t1)
move $a0, $t0
jalr $t2
move $t1, $v0
move $a0, $s2
move $a1, $t1
jalr $s3
move $t0, $v0
j L56
L55: 
nop
L56: 
nop
li $t0, 1
move $v0, $t0
lw $s0, -12($fp)
lw $s1, -16($fp)
lw $s2, -20($fp)
lw $s3, -24($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 28
jr $ra



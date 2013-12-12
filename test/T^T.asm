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
la $t2, Fac_ComputeFac
sw $t2, 0($t0)
sw $t0, 0($t1)
move $t0, $t1
lw $t1, 0($t0)
lw $t2, 0($t1)
li $t1, 3
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
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 12
jr $ra

.text
.globl Fac_ComputeFac
Fac_ComputeFac:
sw $fp, -8($sp)
sw $ra, -4($sp)
move $fp, $sp
subu $sp, $sp, 24
sw $s0, -8($fp)
sw $s1, -12($fp)
sw $s2, -16($fp)
move $s0, $a0
move $s1, $a1
li $t0, 1
move $v1, $t0
slt $v0, $s1, $v1
move $t1, $v0
beqz $t1 L2
li $s2, 1
j L3
L2: 
nop
move $t0, $s0
lw $t1, 0($t0)
lw $t2, 0($t1)
li $t1, 1
move $v1, $t1
sub $v0, $s1, $v1
move $t3, $v0
move $a0, $t0
move $a1, $t3
jalr $t2
move $t1, $v0
move $v1, $t1
mul $v0, $s1, $v1
move $t0, $v0
move $s2, $t0
L3: 
nop
move $v0, $s2
lw $s0, -8($fp)
lw $s1, -12($fp)
lw $s2, -16($fp)
lw $ra, -4($fp)
lw $fp, -8($fp)
addu $sp, $sp, 24
jr $ra


